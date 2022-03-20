package com.munirderman.fragments.usefragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.munirderman.R
import com.munirderman.adapters.IOnBackPressed
import com.munirderman.dbhelper.ButunSohbetler
import kotlinx.android.synthetic.main.fragment_sqlview.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SqlViewFragment() : AbstractSqlFragment(),IOnBackPressed {

    private lateinit var butunSohbetler: ButunSohbetler
    private lateinit var butunSohbetlerListe: MutableList<ButunSohbetler>

    private val RECOVERY_DIALOG_REQUEST = 1

    constructor(
        butunSohbetler: ButunSohbetler,
        butunSohbetlerListe: MutableList<ButunSohbetler>
    ) : this() {
        this.butunSohbetler = butunSohbetler
        this.butunSohbetlerListe = butunSohbetlerListe
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val youTubePlayerFragment =  requireActivity().supportFragmentManager.findFragmentById(R.id.official_player_view) as YouTubePlayerFragment?
////        val youTubePlayerFragment =  requireActivity().supportFragmentManager.findFragmentById(R.id.official_player_view) as YouTubePlayerSupportFragment?
//        youTubePlayerFragment?.initialize("AIzaSyDZ9YMNRnXVTjeXMbTxANovvFnZwfHVWrA", this)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setContentView(R.layout.activity_youtube_player_api)
//        requireActivity().actionBar?.setTitle(butunSohbetler.Title)
        webview_player_view.settings.javaScriptEnabled = true
        webview_player_view.loadUrl(butunSohbetler.VideoLink)

        (activity as? AppCompatActivity)?.supportActionBar?.title = butunSohbetler.Title
//        activity?.actionBar?.title = butunSohbetler.Title
        sohbetler.setText(butunSohbetler.Description)
        sohbetler.setMovementMethod(ScrollingMovementMethod())



        viewprevioussql.setOnClickListener() {

            pagination(butunSohbetler.Id - 1)

        }
        viewnextsql.setOnClickListener() {

            pagination(butunSohbetler.Id + 1)

        }

    }



    fun pagination(count: Int) {

        if (count >= 0 && count < butunSohbetlerListe.count()) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, SqlViewFragment(butunSohbetlerListe[count],butunSohbetlerListe)).commit()
        }else{
            Toast.makeText(requireContext(), "Son sayfa!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed(): Boolean {

        requireActivity().title = getString(R.string.single_choice_recycler_name)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, SqlChoiceRecyclerFragment()).commit()
        return false
//        val count: Int = requireActivity().supportFragmentManager.getBackStackEntryCount()
//        return if (count == 0) {
//            //action not popBackStack
//            true
//        } else {
//            requireActivity().supportFragmentManager.popBackStack()
//            false
//        }

    }
}



