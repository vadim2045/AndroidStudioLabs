package com.example.Lab9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.Lab9.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var database: RoutesDB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,
            RoutesDB::class.java, "route_database"
        ).build()
        val routeDao = database.routeDao()

        binding.addBtn.setOnClickListener{
            val name = binding.name.text.toString()
            val description = binding.description.text.toString()
            val length = binding.length.text.toString().toInt()
            val route = Route(name = name, description = description, length = length)
            GlobalScope.launch {
                routeDao.insertAll(route)
            }
            Toast.makeText( applicationContext, "route created", Toast.LENGTH_LONG).show()
        }

        binding.allRoutes.setOnClickListener{
            GlobalScope.launch {
                val routes = routeDao.getAll()
                var routesInfo = ""
                routes.forEach{
                    routesInfo += "${it.id}: ${it.name} ${it.description} ${it.length}\n"
                }
                runOnUiThread{
                    binding.textView.text = routesInfo
                }
            }
        }

        binding.deleteButton.setOnClickListener{
            val routeId = binding.idText.text.toString().toIntOrNull()

            if (routeId == null || routeId < 0) {
                Toast.makeText(this, "Invalid index.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                routeDao.deleteById(routeId)

                GlobalScope.launch {
                    val routes = routeDao.getAll()
                    var routesInfo = ""
                    routes.forEach{
                        routesInfo += "${it.id}: ${it.name} ${it.description} ${it.length}\n"
                    }
                    runOnUiThread{
                        binding.textView.text = routesInfo
                    }
                }
            }
        }

        GlobalScope.launch {
            val routes = routeDao.getAll()
            var routesInfo = ""
            routes.forEach{
                routesInfo += "${it.id}: ${it.name} ${it.description} ${it.length}\n"
            }
            runOnUiThread{
                binding.textView.text = routesInfo
            }
        }
    }
}

