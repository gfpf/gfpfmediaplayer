package br.com.gfpfmediaplayer.ui.media

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gfpfmediaplayer.R
import br.com.gfpfmediaplayer.service.domain.MediaItem
import br.com.gfpfmediaplayer.service.domain.MediaList
import br.com.gfpfmediaplayer.service.domain.MediaListContract
import br.com.gfpfmediaplayer.view.CardItemDecoration
import br.com.gfpfmediaplayer.view.MediaListRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_media_list.*

class MediaListFragment : Fragment()
    , MediaListContract.View
    , MediaListRecyclerViewAdapter.RecyclerViewClickListener {

    private var mAdapter: MediaListRecyclerViewAdapter? = null
    private lateinit var mMediaListViewModel: MediaListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_media_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        doInitialLoad()
    }

    private fun init() {
        mMediaListViewModel =
            ViewModelProviders.of(this).get(MediaListViewModel::class.java)

        // Set up the RecyclerView
        recycler_view?.setHasFixedSize(true)
        //recycler_view.itemAnimator =

        val layoutManager = LinearLayoutManager(context)
        recycler_view?.layoutManager = layoutManager

        val smallPadding = resources.getDimensionPixelSize(R.dimen.margin_16)
        val itemDecoration = CardItemDecoration(smallPadding, smallPadding)
        recycler_view?.addItemDecoration(itemDecoration)

        //Adapter
        if (mAdapter == null) {
            mAdapter = MediaListRecyclerViewAdapter(this)
        } else {
            result_label?.visibility = View.GONE
            recycler_view?.visibility = View.VISIBLE
        }
        recycler_view?.adapter = mAdapter
    }

    @SuppressLint("CheckResult")
    private fun doInitialLoad() {

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

        if (mediaList == null || mediaList.mMediaItems.isEmpty()) {
            result_label?.visibility = View.VISIBLE
            recycler_view?.visibility = View.GONE
        } else {
            result_label?.visibility = View.GONE
            recycler_view?.visibility = View.VISIBLE

            if (isAppend) {
                mAdapter?.appendData(mediaList.mMediaItems as MutableList<MediaItem>)

            } else {
                mAdapter?.replaceData(mediaList.mMediaItems as MutableList<MediaItem>)
            }
        }
    }

    override fun recyclerViewListClicked(v: View, position: Int) {

    }

    override fun showMediaItemDetailUI(requestedMedia: MediaItem) {
    }

    override fun showToastMessage(message: String) {
    }

    override fun setProgressIndicator(active: Boolean) {
        if (isAdded && active) {
            progress_bar?.visibility = View.VISIBLE

        } else {
            progress_bar?.visibility = View.GONE
        }
    }
}