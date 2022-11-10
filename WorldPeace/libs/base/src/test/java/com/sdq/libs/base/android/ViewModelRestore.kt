package com.sdq.libs.base.android

/**
 *
 * @PackageName com.sdq.libs.base.android
 * @date 2022/11/10 13:45
 * @author songdongqi
 */
class ViewModelRestore {
    /**
     * fragment 创建流程
     * supportFragmentManager->FragmentActivity.getSupportFragmentManager
     * FragmentController.getSupportFragmentManager()
     * final FragmentManager mFragmentManager = new FragmentManagerImpl();
     *
     * supportFragmentManager.beginTransaction()
     */

    /**
     * ViewModelStore 创建流程
     * Interface ViewModelStoreOwner getViewModelStore()
     * fragment ： ViewModelStoreOwner
     * fragment.getViewModelStore()->mFragmentManager.getViewModelStore(this)
     * FragmentManager：
     * private FragmentManagerViewModel mNonConfig;
     * mNonConfig.getViewModelStore(f：Fragment);
     *
     * 一个Fragment，一个ViewModelStore
     * FragmentManagerViewModel：
     * 重点**** private final HashMap<String, ViewModelStore> mViewModelStores = new HashMap<>();
     * viewModelStore = new ViewModelStore();
     * mViewModelStores.put(f.mWho, viewModelStore);——f.mWho:UUID.random
     * ViewModelStore：HashMap<String, ViewModel> mMap = new HashMap<>();
     */

    /**
     * ComponentActivity.getViewModelStore();
     * ensureViewModelStore()
     * 重点*****Restore the ViewModelStore from NonConfigurationInstances
     * mViewModelStore = nc.viewModelStore;
     * ViewModelProvider
     * viewModelProvider.get(FragmentManagerViewModel.class);
     * FragmentManagerViewModel.getViewModelStore(f：Fragment);
     */
}