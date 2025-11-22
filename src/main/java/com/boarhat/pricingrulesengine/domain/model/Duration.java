package com.boarhat.pricingrulesengine.domain.model;

import java.time.Period;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Duration {
    private static final Pattern DURATION_PATTERN = Pattern.compile("(\\d+)\\s+(months?|weeks?|years?)");

    private final Period period;

    private Duration(Period period) {
        this.period = period;
    }

    public static Duration of(String duration) {
        Objects.requireNonNull(duration, "Duration cannot be null");

        Matcher matcher = DURATION_PATTERN.matcher(duration);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid duration: " + duration);
        }

        int number =  Integer.parseInt(matcher.group(1));
        String timeUnit =  matcher.group(2);

        if (number <= 0) {
            throw new IllegalArgumentException("Invalid duration: " + duration);
        }

        Period period = switch (timeUnit) {
            case "week", "weeks" -> Period.ofWeeks(number);
            case "month", "months" -> Period.ofMonths(number);
            case "year", "years" -> Period.ofYears(number);
            default -> throw new IllegalArgumentException("Invalid duration: " + duration);
        };

        return new Duration(period);
    }

    public static Duration of(Period period) {
        Objects.requireNonNull(period, "Period cannot be null");
        return new Duration(period);
    }

    public boolean isGreaterThan(Duration duration) {
        return this.toTotalDays() > duration.toTotalDays();
    }

    public boolean isLessThan(Duration duration) {
        return this.toTotalDays() < duration.toTotalDays();
    }

    private int toTotalDays() {
        // Approximate: 1 month ≈ 30 days, 1 year = 365 days
        return period.getYears() * 365
                + period.getMonths() * 30
                + period.getDays();
    }

    @Override
    public String toString() {
        return "Duration{" +
                "period=" + period +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Duration duration)) return false;
        return Objects.equals(period, duration.period);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(period);
    }
}
