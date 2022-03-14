package com.example.demo88

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class OrderTest {

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var couponRepository: CouponRepository

    @Autowired
    private lateinit var reservationRepository: ReservationRepository

    @Test
    fun test() {
        val reservation = reservationRepository.save(Reservation())
//        val reservation = null
        val coupon = couponRepository.save(Coupon(reservation = reservation))

        entityManager.flush()
        entityManager.clear()

        couponRepository.findById(1L)
    }
}