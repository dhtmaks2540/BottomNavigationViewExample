//package kr.co.lee.bottomnavigationviewexample
//
//import androidx.activity.viewModels
//import androidx.databinding.BindingAdapter
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.commit
//import com.google.android.material.bottomnavigation.BottomNavigationView
//
//object ViewBinding {
//    @JvmStatic
//    @BindingAdapter("menuItemSelected")
//    fun bindMenuItemSelected(bottomNavigationView: BottomNavigationView, viewModel: MainViewModel) {
//        bottomNavigationView.setOnItemSelectedListener { menuItem ->
//            viewModel.setCurrentFragment(menuItem.itemId)
//        }
//    }
//}
//
//private fun getFragment(fragmentType: FragmentType): Fragment {
//    return SampleFragment.newInstance(fragmentType.fragmentTitle)
//}
//
//private val mainViewModel by viewModels<MainViewModel>()
//
//binding.apply {
//    viewModel = mainViewModel
//    lifecycleOwner = this@MainActivity
//}
//
//mainViewModel.currentFragmentType.observe(this) {
//    changeFragment(it)
//}
//
//private fun changeFragment(fragmentType: FragmentType) {
//    var targetFragment = supportFragmentManager.findFragmentByTag(fragmentType.fragmentTag)
//
//    supportFragmentManager.commit {
//        if(targetFragment == null) {
//            targetFragment = getFragment(fragmentType)
//            add(R.id.container_main, targetFragment!!, fragmentType.fragmentTag)
//        }
//
//        show(targetFragment!!)
//
//        FragmentType.values()
//            .filterNot { it == fragmentType }
//            .forEach { type ->
//                supportFragmentManager.findFragmentByTag(type.fragmentTag)?.let {
//                    hide(it)
//                }
//            }
//    }
//}