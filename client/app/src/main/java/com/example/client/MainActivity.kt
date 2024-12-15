package com.example.client

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.client.databinding.ActivityMain2Binding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    var isAuthorized: Boolean = false

    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)

        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets



        }

        //        ТУТ будет логика перекидываания и тд а пока просто запускает другой интент






// В TargetActivity данные получаются так:
//        val value1 = intent.getStringExtra("key1")
//        val value2 = intent.getIntExtra("key2", 0) // 0 - значение по умолчанию, если ключ не найден


    }
    override fun onStart() {
        super.onStart()
        val intent = Intent(this, LoginActivity::class.java) // this - контекст текущего Activity
// Можно передать данные в TargetActivity:
//        intent.putExtra("key1", "value1")
//        intent.putExtra("key2", 123)
        //Thread.sleep(20_000)
        startActivity(intent)

        if (isAuthorized == true)
        {
            startActivity(intent)
        }
        else {
            binding.button.setOnClickListener {
                startActivity(intent)
        }
        //delay(20_000)


        }
    }
}