package com.sdq.libs.base.android

/**
 *
 * @PackageName com.sdq.libs.base.android
 * @date 2022/8/22 9:20
 * @author songdongqi
 */
class FragmentRestore {
    //状态保存
    /**
     * FragmentManager.java
     * static final String SAVED_STATE_TAG = "android:support:fragments";
     * 注册了一个 SavedStateProvider-》Bundle saveState(); lambda 表达式
     * registry.registerSavedStateProvider
     */

    /**
     * ComponentActivity.java
     * protected void onSaveInstanceState(@NonNull Bundle outState)
     * mSavedStateRegistryController.performSave(outState);
     *
     * SavedStateRegistryController.java
     * mRegistry.performSave(outBundle);
     *
     * SavedStateRegistry.java
     * performSave
     * components.putBundle(entry1.getKey(), entry1.getValue().saveState()); 回调注册的SavedStateProvider
     */

    //view保存
    /**
     * FragmentStateManager.java
     * saveViewState()
     * mFragment.mView.saveHierarchyState(mStateArray);
     * View.java
     * dispatchSaveInstanceState(container);
     * ViewGroup.java
     * dispatchSaveInstanceState(container);
     *     View c = children[i];
     *    if ((c.mViewFlags & PARENT_SAVE_DISABLED_MASK) != PARENT_SAVE_DISABLED) {
     *    c.dispatchSaveInstanceState(container);
     *
     */

    /**
     * Fragment.java
     * final void restoreViewState(Bundle savedInstanceState)
     */


}