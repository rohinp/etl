package com.inter.etl

trait Record
case class Record1[T1](fields:(Field[T1])) extends Record
case class Record2[T1,T2](fields:(Field[T1],Field[T2])) extends Record



