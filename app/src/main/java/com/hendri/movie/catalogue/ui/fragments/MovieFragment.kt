
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.data.api.ApiHelperImp
import com.hendri.movie.catalogue.data.api.RetrofitBuilder
import com.hendri.movie.catalogue.data.local.DatabaseBuilder
import com.hendri.movie.catalogue.data.local.DatabaseHelperImp
import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.databinding.FragmentMovieBinding
import com.hendri.movie.catalogue.ui.adapters.MovieAdapter
import com.hendri.movie.catalogue.ui.base.ViewModelFactory
import com.hendri.movie.catalogue.ui.listeners.ItemListener
import com.hendri.movie.catalogue.ui.viewmodels.MovieViewModel
import com.hendri.movie.catalogue.utils.Status
import timber.log.Timber
import java.util.ArrayList


class MovieFragment : Fragment(), ItemListener {

    private lateinit var fragmentBinding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private var movies : List<Movie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentBinding.isLoading = true
        setupViewModel()
        setupObserver()
        doInitialization()
    }

    private fun doInitialization() {
        movieAdapter = MovieAdapter(this)
        movieAdapter.setData(movies)
        fragmentBinding.rvMovie.setHasFixedSize(true)
        fragmentBinding.rvMovie.adapter = movieAdapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(
            ApiHelperImp(RetrofitBuilder.apiService),
            DatabaseHelperImp(DatabaseBuilder.getInstance(requireContext().applicationContext))
            )
        ).get(MovieViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.getMovieFromApi().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 ->
                        fragmentBinding.isLoading = false
                        movies = it1
                        movieAdapter.setData(movies)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
                Status.LOADING -> {
                    fragmentBinding.isLoading = true
                }
                Status.ERROR -> {
                    fragmentBinding.isLoading = false
                    it.message?.let { it1 -> requireContext().toast(it1) }
                }
            }
        })
    }

    override fun onItemClicked(dataEntity: DataEntity) {

    }

    override fun onItemClicked(movie: Movie) {
        Timber.d("Trace :: data(${movie.title})")
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}