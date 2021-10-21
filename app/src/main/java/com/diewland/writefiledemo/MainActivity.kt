package com.diewland.writefiledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.diewland.log2file.DailyLog
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
            for (i in 1..round) {
                l.write(i.toString())
            }

            // after write
            l.read().forEach { Log.d(tag, it) }
            Log.d(tag, l.readText())
        }

        // button 2 --- diary log
        val path = "/sdcard/log"
        val prefix = "private"
        val ext = "log"
        findViewById<Button>(R.id.btn_two).setOnClickListener {
            val p = "Scientists' research has revealed that viruses are by far the most abundant life forms on Earth. There are a million times more viruses on the planet than stars in the universe. Viruses also harbor the majority of genetic diversity on Earth. Scientists are finding evidence of viruses as a planetary force, influencing the global climate and geochemical cycles. They have also profoundly shaped the evolution of their hosts. The human genome, for example, contains 100,000 segments of virus DNA."
            // simulate month logs
            (1..31).forEach { d ->
                // create 1K lines log
                val yyyymmdd = "202110%02d".format(d)
                (1..1000).forEach {
                    DailyLog.write(
                        "%05d %s".format(it, p),
                        path,
                        prefix,
                        ext,
                        yyyymmdd,
                    )
                }
            }
        }
        findViewById<Button>(R.id.btn_two_one).setOnClickListener {
            DailyLog.clean(10, path, prefix, ext)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DailyLog.release()
    }
}