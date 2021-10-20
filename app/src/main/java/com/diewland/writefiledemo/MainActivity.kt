package com.diewland.writefiledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.diewland.log2file.Log2File

class MainActivity : AppCompatActivity() {

    private val tag = "LOG2FILE_MAIN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // button 1 --- fix line size
        findViewById<Button>(R.id.btn_one).setOnClickListener {
            // config
            val path = "/sdcard/demo.txt"
            val limit = 10
            val round = 1_000

            // init log
            val l = Log2File(path, limit)

            // before write
            l.read().forEach { Log.d(tag, it) }
            Log.d(tag, l.readText())

            // write N round
            for (i in 1..round) { l.write(i.toString()) }

            // after write
            l.read().forEach { Log.d(tag, it) }
            Log.d(tag, l.readText())
        }

        // button 2 --- diary log
        findViewById<Button>(R.id.btn_two).setOnClickListener {
            // TODO
        }
    }
}