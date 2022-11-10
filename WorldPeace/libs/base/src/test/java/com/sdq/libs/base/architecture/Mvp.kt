package com.sdq.libs.base.architecture

/**
 *
 * @PackageName com.sdq.libs.base.architecture
 * @date 2022/11/10 10:03
 * @author songdongqi
 */
class Mvp {
}

interface BaseView<T> {
    fun setPresenter(presenter: T)
}

interface BasePresenter {
    /**
     * 数据初始化
     */
    fun start()
}

interface TaskDetailContract {
    interface View : BaseView<Presenter> {
        fun showTitle()
    }

    interface Presenter : BasePresenter {
        fun editTask()
    }
}

class TaskDetailActivity {
    fun onCreate() {
        val fragment = TaskDetailFragment()
        val presenter = TaskDetailPresenter(fragment)
    }
}

class TaskDetailFragment : TaskDetailContract.View {
    lateinit var mPresenter: TaskDetailContract.Presenter

    fun onResume() {
        mPresenter.start()
    }

    override fun setPresenter(presenter: TaskDetailContract.Presenter) {
        mPresenter = presenter
    }

    override fun showTitle() {
        TODO("Not yet implemented")
    }
}

class TaskDetailPresenter constructor(
    private val view: TaskDetailContract.View
) : TaskDetailContract.Presenter {
    init {
        view.setPresenter(this)
    }

    override fun start() {
        view.showTitle()
    }

    override fun editTask() {
        TODO("Not yet implemented")
    }
}