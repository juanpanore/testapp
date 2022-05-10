package com.example.testapp.activities.detallemensaje

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testapp.R
import com.example.testapp.activities.main.fragments.MensajesFragment
import com.example.testapp.databinding.ActivityDetalleMensajesBinding


class DetalleMensajeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding:  ActivityDetalleMensajesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleMensajesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        findViewById<TextView>(R.id.tv_body_dm).apply {
            text = intent.extras?.getString("body") ?: "body"
        }
        findViewById<TextView>(R.id.tv_title_dm).apply {
            text = intent.extras?.getString("title") ?: "title"
        }
        findViewById<TextView>(R.id.tv_from_dm).apply {
            text = intent.extras?.getString("from") ?: "from"
        }
        findViewById<TextView>(R.id.tv_time_dm).apply {
            text = intent.extras?.getString("time") ?: "time"
        }


        findViewById<ImageView>(R.id.iv_mensaje_dm).apply {
            Glide.with(this).load(intent.extras?.getString("imageContactUrl") ?: "imageContactUrl")
                .diskCacheStrategy(
                    DiskCacheStrategy.NONE
                )
        }



        /*



       val navController = findNavController(R.id.nav_host_fragment_content_main)
          appBarConfiguration = AppBarConfiguration(navController.graph)
          setupActionBarWithNavController(navController, appBarConfiguration)
          binding.tvTime.text = intent.extras?.getString("title")?:"title"
          binding.tvFrom.text = intent.extras?.getString("from")?:"from"
          binding.tvBody.text = intent.extras?.getString("body")?:"body"
          Glide.with(this).load(intent.extras?.getString("imageContactUrl")?:"imageContactUrl").diskCacheStrategy(
              DiskCacheStrategy.NONE
          ).into(binding.ivMensaje)
          binding.tvTime.text = intent.extras?.getString("time")?:"time"

          println(binding.tvTitle.text)

          println(binding.tvBody.text)

          println(binding.tvFrom.text)*/

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*   override fun onSupportNavigateUp(): Boolean {
           val navController = findNavController(R.id.nav_host_fragment_content_main)
           return navController.navigateUp(appBarConfiguration)
                   || super.onSupportNavigateUp()
       }*/
}
