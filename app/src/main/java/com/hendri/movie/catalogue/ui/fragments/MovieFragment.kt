import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.source.local.entity.Movie
import com.hendri.movie.catalogue.data.source.remote.vo.Status
import com.hendri.movie.catalogue.databinding.FragmentMovieBinding
import com.hendri.movie.catalogue.ui.activities.DetailActivity
import com.hendri.movie.catalogue.ui.adapters.MovieAdapter
import com.hendri.movie.catalogue.ui.listeners.MovieListener
import com.hendri.movie.catalogue.ui.viewmodels.MovieViewModel
import com.hendri.movie.catalogue.utils.Constants
import com.hendri.movie.catalogue.viewmodel.ViewModelFactory
import timber.log.Timber


class MovieFragment : Fragment(), MovieListener {

    private lateinit var fragmentBinding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private var movies = mutableListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
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
        val factory = ViewModelFactory.getInstance(activity as AppCompatActivity)
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
    }

    private fun setupObserver() {

        viewModel.getMovies().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 ->
                        fragmentBinding.isLoading = false
                        movieAdapter.setData(it.data)
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

    override fun onItemClicked(movie: Movie) {
        Timber.d("Trace :: data(${movie.title})")
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, movie.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, Constants.TYPE_MOVIE)
        requireActivity().startActivity(intent)
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}