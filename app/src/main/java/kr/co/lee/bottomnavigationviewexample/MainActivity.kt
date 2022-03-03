package kr.co.lee.bottomnavigationviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kr.co.lee.bottomnavigationviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            bnvMain.setOnItemSelectedListener { menuItem ->
                changeFragment(menuItem.itemId)
                true
            }
        }

        // init
        changeFragment(R.id.menu_one)

        setContentView(binding.root)
    }

    // MenuItemid에 따른 Fragment 변경
    private fun changeFragment(menuItemId: Int) {
        val targetFragment = getFragment(menuItemId)

        // FragmentKTX의 commit 메소드 사용
        supportFragmentManager.commit {
            // targetFragment로 변경
            replace(R.id.container_main, targetFragment)
        }
    }

    // MenuItemId에 따른 Fragment 생성
    private fun getFragment(menuItemId: Int): Fragment {
        val title = when(menuItemId) {
            R.id.menu_one -> "Fragment1"
            R.id.menu_two -> "Fragment2"
            R.id.menu_three -> "Fragment3"
            else -> throw IllegalArgumentException("Not found menu item")
        }

        return SampleFragment.newInstance(title)
    }
}

enum class FragmentType(val fragmentTitle: String, val fragmentTag: String) {
    FRAGMENT1("fragment1", "fragment1_tag"),
    FRAGMENT2("fragment2", "fragment2_tag"),
    FRAGMENT3("fragment3", "fragment3_tag");
}