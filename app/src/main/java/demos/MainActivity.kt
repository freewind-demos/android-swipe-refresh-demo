package demos

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        val textView = findViewById<TextView>(R.id.textView)
        
        swipeRefresh.setOnRefreshListener {
            Handler().postDelayed({
                textView.text = "数据已刷新"
                swipeRefresh.isRefreshing = false
            }, 2000)
        }
    }
}
