package io.realm.kotlin.demo.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.realmListOf

class Counter: RealmObject {
    @PrimaryKey
    var _id: String = "primary"

    // Why is this required?
    var realm_id: String? = "my-partition"

    // We haven't migrated RealmMutableInteger yet, so emulate it using a list.
    var list: RealmList<Int> = realmListOf()
}