package br.com.gfpfmediaplayer.ui.media

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import br.com.gfpfmediaplayer.R
import br.com.gfpfmediaplayer.service.domain.MediaItem
import br.com.gfpfmediaplayer.service.domain.MediaList
import br.com.gfpfmediaplayer.service.domain.MediaListContract

class MediaListFragment : Fragment(), MediaListContract.View {

    private var recyclerView: RecyclerView? = null
    private var results: TextView? = null
    private var progressBar: ProgressBar? = null

    private lateinit var mMediaListViewModel: MediaListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mMediaListViewModel =
            ViewModelProviders.of(this).get(MediaListViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_media_list, container, false)
        recyclerView = root.findViewById(R.id.recycler_view)
        results = root.findViewById(R.id.result_label)
        progressBar = root.findViewById(R.id.progress_bar)

        doInitialLoad()

        return root
    }

    @SuppressLint("CheckResult")
    private fun doInitialLoad() {
        //if (savedInstanceState == null) {
        //Load all users
        setProgressIndicator(true)
        mMediaListViewModel.loadAllMediaItem()?.subscribe({ result ->
            //Result
            if (result != null) {
                showMediaListUI(result, false)
            }

        }, { throwable ->
            // handle error event
            showMediaListUI(null, true)
        })
    }

    override fun showMediaListUI(mediaList: MediaList?, isAppend: Boolean) {
        setProgressIndicator(false)

        /*if (items == null || items.isEmpty()) {
            resultsLabel.setVisibility(View.VISIBLE)
            recyclerView.setVisibility(View.GONE)
        } else {
            resultsLabel.setVisibility(View.GONE)
            recyclerView.setVisibility(View.VISIBLE)

            if (isAppend) {
                mAdapter.appendData(items)

            } else {
                mAdapter.replaceData(items)
            }
        }*/
    }

    override fun showMediaItemDetailUI(requestedMedia: MediaItem) {
    }

    override fun showToastMessage(message: String) {
    }

    override fun setProgressIndicator(active: Boolean) {
        if (isAdded && active) {
            progressBar?.visibility = View.VISIBLE

        } else {
            progressBar?.visibility = View.GONE
        }
    }
}