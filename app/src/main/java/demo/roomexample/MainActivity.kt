package demo.roomexample

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val database by lazy {Room.databaseBuilder(applicationContext, ItemsDatabase::class.java, "cleverDatabaseName").build()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val item = Item(name = "hoge", place="fuga", quantity = "foo", description = "bar", tags = "baz")
        Observable.just(item)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({ item ->
                    database.itemDao().insertItem(item)
                })
        onGetItem()
    }

    private fun onGetItem() = database.itemDao().getAllItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                data ->
                Log.d("APP", data.toString())
            },
                    {
                        throwable ->
                        Log.d("APP", "error occuard")
                    })
}
