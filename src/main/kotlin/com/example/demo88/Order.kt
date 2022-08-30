package com.example.demo88

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.persistence.*

@RestController
class CouponController(
    val couponRepository: CouponRepository,
    val couponPlanRepository: CouponPlanRepository,
    val entityManager: EntityManager,
) {

    @GetMapping("/coupons")
    fun coupons(): String {
        val coupon = couponRepository.findById(1L).get()
        return coupon.reservation!!.name
    }


    @PostMapping("/coupon-plan")
    fun saveCouponPlan() {
        couponPlanRepository.save(CouponPlan(name = "test plan", name2 = "1"))
//        entityManager.flush()
        entityManager.clear()
    }

    @PostMapping("/coupons")
    fun saveCoupon() {
        couponRepository.save(
            Coupon(
                name = "coupon name",
                couponPlan = CouponPlan(id = 1L, name = "test plan", name2 = "1"),
                reservation = Reservation(id = 1L, name = "tester"),
            )
        )
//        entityManager.flush()
        entityManager.clear()
    }

    @PostMapping("/reservations")
    @Transactional
    fun saveReservation() {
        val coupon = couponRepository.findById(1L).get()
//        coupon.reservation = Reservation(10L, name = "tester")
    }
}

@Entity
class Coupon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L,

    val name: String,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var reservation: Reservation? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_plan_id")
    var couponPlan: CouponPlan
)

@Entity
class CouponPlan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L,

    @Column(name = "name")
    val name: String,

    @Column(name = "name", insertable = false, updatable = false)
    val name2: String = "",
)

@Entity
class Reservation(
    @Id
    val id : Long = 0L,

    val name: String,
)

interface CouponRepository: JpaRepository<Coupon, Long>
interface CouponPlanRepository: JpaRepository<CouponPlan, Long>