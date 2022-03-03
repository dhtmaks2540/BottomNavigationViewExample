package kr.co.lee.bottomnavigationviewexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _currentFragmentType = MutableLiveData(FragmentType.FRAGMENT1)
    private val _count = MutableLiveData<Int>()
    val currentFragmentType: LiveData<FragmentType>
        get() = _currentFragmentType
    val count: LiveData<Int>
        get() = _count

    fun addCount() {
        _count.value?.plus(1)
    }

    // bottomNavigationView Menu 에 따른 FragmentType 변경
    fun setCurrentFragment(menuItemId: Int): Boolean {
        val pageType = getPageType(menuItemId)
        changeCurrentFragmentType(pageType)

        return true
    }

    // menuItemId 에 따른 FragmentType 반환
    private fun getPageType(menuItemId: Int): FragmentType {
        return when(menuItemId) {
            R.id.menu_one -> FragmentType.FRAGMENT1
            R.id.menu_two -> FragmentType.FRAGMENT2
            R.id.menu_three -> FragmentType.FRAGMENT3
            else -> throw IllegalArgumentException("Not found menu item")
        }
    }

    // 현재 FragmentType 과 비교하여 같으면 return, 다르면 변경
    private fun changeCurrentFragmentType(fragmentType: FragmentType) {
        if(currentFragmentType.value == fragmentType) return

        _currentFragmentType.value = fragmentType
    }
}