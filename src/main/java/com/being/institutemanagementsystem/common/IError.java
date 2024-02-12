package com.being.institutemanagementsystem.common;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

public interface IError {
 String name();

    default String formattedMessage(Object... messageArguments) {
        return formattedMessage(getLocale(), messageArguments);
    }
}
