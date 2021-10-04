package io.realm.kotlin.demo.shared.model

import io.realm.Realm
import io.realm.internal.platform.runBlocking
import io.realm.kotlin.demo.shared.model.entity.Counter
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

    // TODO Inject
    private lateinit var realm: Realm
    private val counterObj: Counter
    private val app: App = App.create(
        AppConfiguration.Builder("testapp1-nwywh")
            .baseUrl("http://127.0.0.1:9090")
            .build()
    )

    init {
        runBlocking {
            val user = app.login(Credentials.emailPassword("cm@mongodb.com", "123456"))
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
     * When the counter reaches 100, it resets.
     */
    public fun adjust(change: Int) {
        // Use an application wide dispatcher
        CoroutineScope(Dispatchers.Default).launch {
            realm.write {
                // println("Start change on ${this.version()}")
                // println("Find ${counterObj.version()}")
                findLatest(counterObj)?.run {
                    list.add(change)
                }
            }
        }
    }

    /**
     * Listen to changes to the counter.
     */
    fun observeCounter(): Flow<Long> {
        return realm.objects(Counter::class).query("_id = 'primary'").observe()
            .filter {
                if (it.size != 1) {
                    println("Size is: ${it.size}")
                }
                it.size == 1
            }
            .map { it.first() }
            .map {
                it.list.fold(0L,) { sum, el -> sum + el }
            }
    }
}