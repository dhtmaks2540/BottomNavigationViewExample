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
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }

        // Observer 등록
        mainViewModel.currentFragmentType.observe(this) {
            println(it)
            // FragmentType이 변경되면 changeFragment에 FragmentType 인자로 넘김
            changeFragment(it)
        }

        setContentView(binding.root)
    }

    // 현재 Fragment는 show, 나머지 Fragment는 hide
    private fun changeFragment(fragmentType: FragmentType) {
        // 현재 Fragemnt
        var targetFragment = supportFragmentManager.findFragmentByTag(fragmentType.fragmentTag)

        // Fragment Ktx의 commit 함수
        supportFragmentManager.commit {
            // 현재 Fragment가 null이라면
            if (targetFragment == null) {
                // getFragment를 호출하여 Fragment 획득
                targetFragment = getFragment(fragmentType)
                // 현재 Fragment 추가
                add(R.id.container_main, targetFragment!!, fragmentType.fragmentTag)
            }

            // 현재 Fragment show
            show(targetFragment!!)

            // 나머지 Fragment hide
            FragmentType.values()
                .filterNot { it == fragmentType }
                .forEach { type ->
                    supportFragmentManager.findFragmentByTag(type.fragmentTag)?.let {
                        hide(it)
                    }
                }
        }
    }

    // FragmentType을 받아서 Fragment 생성하는 함수
    private fun getFragment(fragmentType: FragmentType): Fragment {
        return SampleFragment.newInstance(fragmentType.fragmentTitle)
    }
}