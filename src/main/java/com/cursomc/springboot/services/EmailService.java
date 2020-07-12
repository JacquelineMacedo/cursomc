package com.cursomc.springboot.services;

import org.springframework.mail.SimpleMailMessage;

import com.cursomc.springboot.domain.Cliente;
import com.cursomc.springboot.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
}