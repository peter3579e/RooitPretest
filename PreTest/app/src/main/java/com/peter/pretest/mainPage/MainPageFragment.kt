package com.peter.pretest.mainPage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.peter.pretest.data.News
import com.peter.pretest.data.Source
import com.peter.pretest.data.source.local.NewsModel
import com.peter.pretest.databinding.FragmentMainpageBinding
import com.peter.pretest.ext.getVmFactory
import io.realm.Realm


class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainpageBinding

    private val viewModel by viewModels<MainPageViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainpageBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val realm = Realm.getDefaultInstance()
        val adapter = NewsAdapter()
        binding.recyclerView.adapter = adapter
        val list = mutableListOf<News>()



        viewModel.newList.observe(viewLifecycleOwner, Observer {
            it?.let {

                it.articles!!.forEachIndexed { index, news ->
                    val title = news.title
                    val name = news.source!!.name
                    val image = news.urlToImage
                    if (title != null && name != null && image != null && !list.contains(it.articles)) {
                        viewModel.saveTask(title, name, image)
                    }
                }
                if (list.isEmpty()) {
                    adapter.submitList(it.articles)
                }
            }
        })


        val result = realm.where(NewsModel::class.java).findAllAsync()
        result.addChangeListener { results ->
            results.forEach {
                Log.d("data001", "$it")
                list.add(News(Source(it.name), it.title, it.image))
                adapter.submitList(list)
            }
        }

        return binding.root
    }


}