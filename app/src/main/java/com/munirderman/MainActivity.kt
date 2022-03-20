package com.munirderman

import android.Manifest
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.fondesa.kpermissions.PermissionStatus
import com.fondesa.kpermissions.allGranted
import com.fondesa.kpermissions.anyPermanentlyDenied
import com.fondesa.kpermissions.anyShouldShowRationale
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.request.PermissionRequest
import com.google.android.material.navigation.NavigationView
import com.munirderman.adapters.*
import com.munirderman.dbhelper.DbHelper
import com.munirderman.fragments.usefragment.PdfChoiceRecyclerFragment
import com.munirderman.fragments.usefragment.PdfViewFragment
import com.munirderman.fragments.usefragment.SqlChoiceRecyclerFragment
import java.io.*


class MainActivity : AppCompatActivity(), PermissionRequest.Listener {
    //
//    data class Sample(val id: Int, val rate: Int, val name: String)
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView

    private val request by lazy {
        permissionsBuilder(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
        ).build()
    }


    //    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.single_choice_action -> {
//                Toast.makeText(this, "Publication", Toast.LENGTH_SHORT).show()
//                Log.i("MainActivity", "Sign out clicked!")
//            }
//            R.id.single_choice_action -> {
//                Toast.makeText(this, "Publication", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        this.drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }


    @Throws(IOException::class)
    private fun copyDataBase(dbname: String) {
        // Open your local db as the input stream
        val myInput: InputStream = applicationContext.getAssets().open(dbname)
        // Path to the just created empty db
        val outFileName: File = applicationContext.getDatabasePath(dbname)
        // Open the empty db as the output stream
        val myOutput: OutputStream = FileOutputStream(outFileName)
        /* transfer bytes from the inputfile to the outputfile */
        val buffer = ByteArray(1024)
        var length: Int
        while (myInput.read(buffer).also { length = it } > 0) {
            myOutput.write(buffer, 0, length)
        }
        // Close the streams
        myOutput.flush()
        myOutput.close()
        myInput.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        pdfView = findViewById(R.id.pdfView)
        request.addListener(this)
        request.send()

//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)
//
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)


        val dbFile = applicationContext.getDatabasePath(DbHelper.DATABASE_NAME)

        if (!dbFile.exists()) {
//            File(applicationContext.filesDir.parent+"databases").listFiles()
            copyDataBase(DbHelper.DATABASE_NAME)

        }



//        db.databaseName = DATABASE_NAME

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        navigationView.itemIconTintList = null

        navigationView.setNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
//            drawerLayout.closeDrawer(GravityCompat.START)
            drawerLayout.close()
            when (id) {
                R.id.single_choice_action -> {
                    displayPdfChoiceRecyclerFragment()
//                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.sql_choice_action -> {
                    displaySqlTextChoiceRecyclerFragment()
//                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.exit_action -> {
                    finishAffinity()
                    true
                }
                else -> {
                    false
                }

            }
        }

        displayPdfChoiceRecyclerFragment()
    }


    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.main_container)
        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
//            super.onBackPressed()


        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onPermissionsResult(result: List<PermissionStatus>) {
        when {
            result.anyPermanentlyDenied() -> finishAffinity()
            result.anyShouldShowRationale() -> finishAffinity()
            result.allGranted() -> showGrantedToast(result)
        }
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

    public fun displayPdfChoiceRecyclerFragment() {
        title = getString(R.string.single_choice_recycler_name)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, PdfChoiceRecyclerFragment()).commit()
    }

    public fun displaySqlTextChoiceRecyclerFragment() {
        title = getString(R.string.sqltext_choice_recycler_name)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, SqlChoiceRecyclerFragment()).commit()
    }

    public fun displayPdfShowFragment() {
        title = getString(R.string.app_name)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, PdfViewFragment()).commit()
    }

//    private fun displayMultipleChoiceRecyclerFragment() {
//        title = getString(R.string.multiple_choice_recycler_name)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, MultipleChoiceRecyclerFragment()).commit()
//    }
//
//    private fun displaySectionRecyclerFragment() {
//        title = getString(R.string.section_recycler_name)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, SectionRecyclerFragment()).commit()
//    }
//
//    private fun displayGestureRecyclerFragment() {
//        title = getString(R.string.gesture_recycler_name)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, GestureRecyclerFragment()).commit()
//    }
//
//    private fun displayGestureSectionRecyclerFragment() {
//        title = getString(R.string.gesture_sections_recycler_name)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, GestureSectionRecyclerFragment()).commit()
//    }
//
//    private fun displayPaginationRecyclerFragment() {
//        title = getString(R.string.pagination_recycler_name)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, PaginationRecyclerFragment()).commit()
//    }
}