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
) {

    @PostMapping("/coupons")
    fun saveCoupon() {
        couponRepository.save(
            Coupon()
        )
    }

    @GetMapping("/coupons")
    fun coupons(): Optional<Coupon> {
        return couponRepository.findById(1L)
    }

    @PostMapping("/reservations")
    @Transactional
    fun saveReservation() {
        val coupon = couponRepository.findById(1L).get()
        coupon.reservation = Reservation(10L, name = "tester")
    }
}

@Entity
class Coupon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L,

    @OneToOne(cascade = [CascadeType.ALL])
    var reservation: Reservation? = null
)

@Entity
class Reservation(
    @Id
    val id : Long = 0L,

    val name: String,
)

interface CouponRepository: JpaRepository<Coupon, Long>
interface ReservationRepository: JpaRepository<Reservation, Long>