package kr.co.lee.bottomnavigationviewexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _currentFragmentType = MutableLiveData(FragmentType.FRAGMENT1)

    val currentFragmentType: LiveData<FragmentType>
        get() = _currentFragmentType
    
    // FragmentType에 따라 currentFragmentType 변경
    fun setCurrentFragment(menuItemId: Int): Boolean {
        val pageType = getPageType(menuItemId)
        changeCurrentFragmentType(pageType)

        return true
    }

    // menuItemId에 따른 FragmentType 반환
    private fun getPageType(menuItemId: Int): FragmentType {
        return when(menuItemId) {
            R.id.menu_one -> FragmentType.FRAGMENT1
            R.id.menu_two -> FragmentType.FRAGMENT2
            R.id.menu_three -> FragmentType.FRAGMENT3
            else -> throw IllegalArgumentException("Not found menu item")
        }
    }

    // 현재 FragmentType과 비교하여 같으면 return, 다르면 변경
    private fun changeCurrentFragmentType(fragmentType: FragmentType) {
        if(currentFragmentType.value == fragmentType) return

        _currentFragmentType.value = fragmentType
    }
}