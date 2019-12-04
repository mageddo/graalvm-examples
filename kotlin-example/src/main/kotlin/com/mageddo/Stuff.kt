package com.mageddo

interface Stuff {
  fun doStuff(msg: String)

  companion object {
    @JvmField
    val DEFAULT_STUFF: Stuff = object : Stuff {
      override fun doStuff(msg: String) {
      }
    }
  }
}
