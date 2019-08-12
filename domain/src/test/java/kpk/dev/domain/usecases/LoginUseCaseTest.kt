package kpk.dev.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import kpk.dev.model.poko.*
import kpk.dev.model.repository.UserRepository
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var userRepo: UserRepository? = null

    var userUseCase: CheckUserUseCase? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userUseCase = CheckUserUseCase(userRepo!!)
    }

    @Test
    fun `test success scenario`() {
        Mockito.`when`(userRepo?.getUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Single.just(generateMockResponse()))
        assertNotNull(userUseCase?.checkUser("", ""))
    }

    @Test
    fun `test fail scenario`() {
        Mockito.`when`(userRepo?.getUser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(null)
        assertNull(userUseCase?.checkUser("", ""))
    }

    @After
    fun tearDown() {
        userUseCase = null
        userRepo = null
    }

    private fun generateMockResponse() = BaseResponse(Data(User(Repositories(listOf(), PageInfo("", true)), "")), listOf<Error>())

    private fun generateNullMockResponse() = BaseResponse(Data(User(Repositories(listOf(), PageInfo("", true)), "")), listOf<Error>())
}