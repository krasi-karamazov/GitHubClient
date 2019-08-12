package kpk.dev.model.resource

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Failure<out T>(val throwable: Throwable) : Resource<T>(){
        override fun equals(other: Any?): Boolean {
            if(other is Failure<*>){
                if(other.throwable.message == throwable.message) {
                    return true
                }
            }
            return false
        }
    }
}