package io.realm.kotlin.demo

import CFlow
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.internal.platform.runBlocking
import io.realm.kotlin.demo.model.Counter
import io.realm.mongodb.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import wrap

/**
 * Repository, tracking the counter.
 */
class CounterRepository {

    // TODO Inject
    private lateinit var realm: Realm
    private val counterObj: Counter
    private val app: App = App.create(AppConfiguration.Builder("testapp1-nwywh")
        .baseUrl("http://127.0.0.1:9090")
        .build())

    init {
        runBlocking {
            val user = app.login(Credentials.emailPassword("cm@mongodb.com", "123456"))
            val config = SyncConfiguration.Builder(
                schema = setOf(Counter::class),
                user = user,
                partitionValue = "my-partition")
                .build()
            realm = Realm.open(config)
        }

        // With no support for setting up initial values, we just do it manually.
        // WARNING: Writing directly on th UI thread is not encouraged.
        counterObj = realm.writeBlocking {
            val objects = objects(Counter::class)
            when (objects.size) {
                0 -> copyToRealm(Counter())
                1 -> objects.first()
                else -> throw IllegalStateException("Too many counters: ${objects.size}")
            }
        }
    }

    public fun adjust(change: Int) {
        // Use an application wide dispatcher
        println("Adjust $change")
        CoroutineScope(Dispatchers.Default).launch {
            realm.write {
                println("Start change")
                findLatest(counterObj)?.run {
                    println("Modify list")
                    list.add(change)
                }
            }
        }
    }

    fun observe(): Flow<Long> {
        return realm.objects(Counter::class).query("_id = 'primary'").observe()
            .filter {
                if (it.size != 1) {
                    println("Size is: ${it.size}")
                }
                it.size == 1
            }
            .map { it.first() }
            .map {
                it.list.fold(0,) { sum, el -> sum + el }
            }
    }

    fun observeCommon(): CFlow<Long> {
        return observe().wrap()
    }
}