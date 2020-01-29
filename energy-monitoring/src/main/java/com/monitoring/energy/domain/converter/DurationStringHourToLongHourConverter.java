package com.monitoring.energy.domain.converter;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DurationStringHourToLongHourConverter {
    public Long convert(String duration) {
        if (Objects.isNull(duration)) {
            throw new NumberFormatException("Can't convert to Long for the empty duration");
        }
        Pattern durationPattern = Pattern.compile("([0-9]+)([h])");
        Matcher matcher = durationPattern.matcher(duration);
        matcher.find();

        String parsedHours = matcher.group(1);
        String parsedDuration = matcher.group(2);

        if (Objects.isNull(parsedHours) || Objects.isNull(parsedDuration)) {
            throw new NumberFormatException("Can't convert to Long for the wrong duration format.");
        }

        return Long.valueOf(parsedHours);
    }
}
