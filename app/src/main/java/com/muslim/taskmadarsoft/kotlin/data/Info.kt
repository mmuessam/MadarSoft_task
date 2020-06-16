package com.muslim.taskmadarsoft.kotlin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Info")
data class Info(

        var name: String,
        var age: String,
        var jobtitle: String,
        var gender: String


) {

    //does it matter if these are private or not?
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}