package com.example.login.usersModule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.login.databinding.FragmentUsersBinding
import com.example.login.mainModule.MainActivity


class UsersFragment : Fragment() {

    //MainActivity
    private var mMainActivity: MainActivity? = null

    private lateinit var mBinding: FragmentUsersBinding
    private lateinit var mViewModel: UserViewModel

    //Variables adapter
    private  lateinit var mAdapter : UserAdapter
    private lateinit var mLayoutManager: GridLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        mViewModel.getResult().observe(this){
            mAdapter.submitList(it.data) //Cuando se hace el llamado al viewModel se enviaa el resultado al adapter
        }



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = FragmentUsersBinding.inflate(layoutInflater, container, false)
        return mBinding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }

    override fun onDestroyView() { //Mostrar el boton de LOGIN de nuevo
        super.onDestroyView()
        mMainActivity = activity as? MainActivity
        mMainActivity!!.binding.loginBtn.visibility = View.VISIBLE
    }


    private fun setUpRecyclerView(){
        mAdapter = UserAdapter()
        mLayoutManager = GridLayoutManager(requireContext(), 1)

        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = mLayoutManager
        }


    }



}