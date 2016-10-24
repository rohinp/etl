package com.inter

package object etl {
  type Column = String
  type Field[T] = (Column,T)
  type Records = Iterator[Record]

  type Path = String

}
