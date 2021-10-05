/*
 * Copyright 2021 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.realm.kotlin.demo.model

import Counter
import io.realm.Realm
import io.realm.internal.platform.runBlocking
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.SyncConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Repository class. Responsible for storing the Counter and expose updates to it.
 */
class CounterRepository {

    // TODO Move App creation to Application Start
    // TODO Move Login to own View and control flow
    private lateinit var realm: Realm
    private val counterObj: Counter
    private val app: App = App.create(AppConfiguration.Builder("realm-kotlin-sync-demo-lhcgs").build())

    init {
        runBlocking {
            val user = app.login(Credentials.emailPassword("foo@bar.com", "123456"))
            val config = SyncConfiguration.Builder(
                schema = setOf(Counter::class),
                user = user,
                partitionValue = "my-partition"
            )
                .build()
            realm = Realm.open(config)
        }

        // With no support for setting up initial values, we just do it manually.
        // WARNING: Writing directly on the UI thread is not encouraged.
        counterObj = realm.writeBlocking {
            val objects = objects(Counter::class)
            when (objects.size) {
                0 -> copyToRealm(Counter())
                1 -> objects.first()
                else -> throw IllegalStateException("Too many counters: ${objects.size}")
            }
        }
    }

    /**
     * Adjust the counter up and down.
     */
    public fun adjust(change: Int) {
        // TODO Use an application wide dispatcher
        CoroutineScope(Dispatchers.Default).launch {
            realm.write {
                findLatest(counterObj)?.run {
                    list.add(change)
                } ?: println("Could not update Counter")
            }
        }
    }

    /**
     * Listen to changes to the counter.
     */
    fun observeCounter(): Flow<Long> {
        return realm.objects(Counter::class).query("_id = 'primary'").observe()
            .filter { it.size == 1 }
            .map { it.first() }
            .map {
                it.list.fold(0L,) { sum, el -> sum + el }
            }
    }
}