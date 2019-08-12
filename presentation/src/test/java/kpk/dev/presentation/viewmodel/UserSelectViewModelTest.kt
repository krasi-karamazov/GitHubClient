package kpk.dev.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kpk.dev.domain.usecases.CheckUserUseCase
import kpk.dev.model.poko.*
import kpk.dev.model.repository.UserRepository
import kpk.dev.model.resource.Resource
import kpk.dev.model.utils.TestSchedulerProvider

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.*
import org.mockito.ArgumentMatchers
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserSelectViewModelTest {

    var userUseCase: CheckUserUseCase = Mockito.mock(CheckUserUseCase::class.java)
    var userRepo: UserRepository = Mockito.mock(UserRepository::class.java)

    var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    @Mock
    var observer: Observer<Resource<BaseResponse>>? = null

    private lateinit var viewModel: UserSelectViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = UserSelectViewModel(userUseCase!!, compositeDisposable!!, TestSchedulerProvider())
    }

    @Test
    fun `test for null return from usecase`() {
        val response = generateMockResponse()
        Mockito.`when`(userRepo.getUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Single.just(response))
        userUseCase.getUserRepository = userRepo
        Mockito.`when`(userUseCase.checkUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Single.just(response))
        viewModel.checkUser("", "").observeForever(observer!!)
        Assert.assertNotNull(viewModel.checkUser("", ""))
        Assert.assertTrue(viewModel.checkUser("", "").hasObservers())
    }

    @Test
    fun `test for success return from usecase`() {
        val response = generateMockResponse()
        Mockito.`when`(userRepo.getUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Single.just(response))
        userUseCase.getUserRepository = userRepo
        Mockito.`when`(userUseCase.checkUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Single.just(response))
        viewModel.checkUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()).observeForever(observer!!)
        Mockito.verify(observer!!).onChanged(Resource.Success(generateMockResponse()))
    }

    @Test
    fun `test for fail return from usecase`() {
        val response = generateMockResponseFail()
        Mockito.`when`(userRepo.getUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Single.just(response))
        userUseCase.getUserRepository = userRepo
        Mockito.`when`(userUseCase.checkUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Single.just(response))
        viewModel.checkUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()).observeForever(observer!!)
        Mockito.verify(observer!!).onChanged(viewModel.resource)
    }

    @After
    fun tearDown() {
        compositeDisposable = null
        observer = null
    }

    private fun generateMockResponse() = BaseResponse(Data(User(Repositories(listOf(), PageInfo("", true)), "")), listOf<Error>())

    private fun generateMockResponseFail() = BaseResponse(Data(User(Repositories(listOf(), PageInfo("", true)), "")), listOf(Error(listOf(), "An error occurred", listOf(), "error type")))
}