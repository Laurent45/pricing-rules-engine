package com.boarhat.pricingrulesengine.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Value object representing a commission rate as a percentage.
 * <p>
 * Valid rates range from 0% to 100%. The rate is immutable and self-validating.
 * The default platform rate is 10%.
 */
public record CommissionRate(BigDecimal value) {
    private static final BigDecimal MIN_RATE = BigDecimal.ZERO;
    private static final BigDecimal MAX_RATE = new BigDecimal("100");
    private static final BigDecimal PERCENTAGE_DIVISOR = new BigDecimal("100");

    public CommissionRate {
        Objects.requireNonNull(value, "Commission rate cannot be null");

        if (value.compareTo(MIN_RATE) < 0) {
            throw new IllegalArgumentException("Commission rate cannot be negative: " + value);
        }

        if (value.compareTo(MAX_RATE) > 0)  {
            throw new IllegalArgumentException("Commission rate must not be greater than 100");
        }
    }

    public static CommissionRate of(double percent) {
        return new CommissionRate(BigDecimal.valueOf(percent));
    }

    public static CommissionRate of(BigDecimal percent) {
        return new CommissionRate(percent);
    }

    public static CommissionRate defaultRate() {
        return new CommissionRate(BigDecimal.TEN);
    }

    public BigDecimal asDecimal() {
        return this.value.divide(PERCENTAGE_DIVISOR, value.scale() + 2, RoundingMode.HALF_UP);
    }
}
