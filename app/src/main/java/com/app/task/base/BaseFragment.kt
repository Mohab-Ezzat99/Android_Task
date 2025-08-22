package com.app.task.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.app.task.util.LoadingDialog

abstract class BaseFragment<B : ViewBinding, V : BaseViewModel>(
    open val bindingInflater: (LayoutInflater) -> B
) : Fragment() {

    abstract fun viewModelClass(): Class<V>

    protected val viewModel: V by lazy { ViewModelProvider(this).get(viewModelClass()) }

    private var _binding: B? = null
    open val binding get() = checkNotNull(_binding)

    private val loadingDialogDelegate = lazy { LoadingDialog(activity) }
    private val loadingDialog by loadingDialogDelegate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setUI(savedInstanceState)
        binding.clicks()
        callApis()
        observer()
    }

    open fun B.setUI(savedInstanceState: Bundle?) {}
    open fun B.clicks() {}
    open fun callApis() {}
    open fun observer() {}

    fun showLoading() = loadingDialog.showDialog()

    fun hideLoading() = loadingDialog.hideDialog()

    override fun onDestroy() {
        _binding = null
        if (loadingDialogDelegate.isInitialized()) {
            loadingDialog.clean()
        }
        super.onDestroy()
    }
}
