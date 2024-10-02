package com.codewithblaise.movieflix.auth.entities;

import lombok.Builder;

@Builder
public record MailBody(String to,String subject,String text) {
}
