package kpk.dev.presentation.userselect.viewmodel

import androidx.lifecycle.Observer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import kpk.dev.domain.usecases.CheckUserUseCase
import kpk.dev.model.datasource.remote.graphql.GitHubGraphQLAPI
import kpk.dev.model.poko.*
import kpk.dev.model.query.UserQuery
import kpk.dev.model.repository.UserRepository
import kpk.dev.model.resource.Resource
import kpk.dev.model.utils.TestSchedulerProvider

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import org.junit.*


@RunWith(JUnit4::class)
class UserSelectViewModelTest {

    @Mock
    var gitHubGraphQLAPI: GitHubGraphQLAPI? = null

    @Mock
    var userUseCase: CheckUserUseCase? = null

    var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    @Mock
    var observer: Observer<Resource<BaseResponse>>? = null

    @Mock
    var userRepo: UserRepository? = null

    private lateinit var viewModel: UserSelectViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = UserSelectViewModel(userUseCase!!, compositeDisposable!!, TestSchedulerProvider())
        //viewModel.checkUser("", "").observeForever(observer!!)
    }

    @Test
    fun `test for null return from usecase`() {
        userUseCase?.getUserRepository = userRepo!!
        Mockito.`when`(userUseCase?.checkUser("", "")).thenReturn(null)
        //Mockito.`when`(userRepo?.getUser("", "")).thenReturn(Single.just(generateMockResponse()))
        Assert.assertNotNull(viewModel.checkUser("", ""))
        Assert.assertTrue(viewModel.checkUser("", "").hasObservers())
    }

    @Test
    fun `test for success return from usecase`() {
        Mockito.`when`(gitHubGraphQLAPI?.checkUser("", UserQuery(""))).thenReturn(Single.just(generateMockResponse()))
        Mockito.`when`(gitHubGraphQLAPI?.checkUser("", UserQuery(""))).thenReturn(Single.just(generateMockResponse()))
        viewModel.checkUser("", "")
        Mockito.verify(observer!!).onChanged(Resource.Loading())
        Mockito.verify(observer!!).onChanged(Resource.Success(generateMockResponse()))
    }

    @After
    fun tearDown() {
        userUseCase = null
        compositeDisposable = null
        gitHubGraphQLAPI = null
        observer = null
    }

    private val immediate = object : Scheduler() {
        override fun scheduleDirect(run: Runnable,
                                    delay: Long, unit: TimeUnit
        ): Disposable {
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Scheduler.Worker {
            return ExecutorScheduler.ExecutorWorker(
                Executor { it.run() }, false)
        }
    }

    private fun generateMockResponse() = BaseResponse(Data(User(Repositories(listOf(), PageInfo("", true)), "")), listOf<Error>())
}