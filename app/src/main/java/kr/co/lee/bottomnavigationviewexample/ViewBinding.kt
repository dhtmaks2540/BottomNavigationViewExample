package kr.co.lee.bottomnavigationviewexample

import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView

object ViewBinding {
    @JvmStatic
    @BindingAdapter("menuItemSelected")
    fun bindMenuItemSelected(
        bottomNavigationView: BottomNavigationView, viewModel: MainViewModel
    ) {
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            viewModel.setCurrentFragment(menuItem.itemId)
        }
    }
}

enum class FragmentType(val fragmentTitle: String, val fragmentTag: String) {
    FRAGMENT1("fragment1", "fragment1_tag"),
    FRAGMENT2("fragment2", "fragment2_tag"),
    FRAGMENT3("fragment3", "fragment3_tag");
}


