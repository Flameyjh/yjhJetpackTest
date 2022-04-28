package com.yjh.yjhjetpacktest.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Insert
    fun insertUser(book: Book): Long

    @Query("select * from Book")
    fun loadAllUsers(): List<Book>
}