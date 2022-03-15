package com.example.demo88

import org.assertj.core.api.Assertions.assertThat
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
    private lateinit var couponPlanRepository: CouponPlanRepository

    @Test
    fun test() {
        couponPlanRepository.save(CouponPlan(name = "test plan"))
        val coupon = couponRepository.save(
            Coupon(
                name = "coupon name",
                couponPlan = CouponPlan(id = 1L, name = "test plan"),
                reservation = Reservation(id = 1L, name = "tester"),
            )
        )

        entityManager.flush()
        entityManager.clear()

        val actual = couponRepository.findById(1L).get()

        assertThat(actual.couponPlan.name).isEqualTo("test plan")
    }
}