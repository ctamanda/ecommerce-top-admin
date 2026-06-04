# =====================================================================
# Sobe a API com a SUA conta Mercado Pago vinculada (modo real).
# 1) Cole abaixo o Access Token e a Public Key da sua aplicacao
#    (painel: https://www.mercadopago.com.br/developers -> Suas integracoes -> Credenciais).
#    Para testar, use as credenciais de TESTE (Access Token comeca com "TEST-").
# 2) Para o webhook do PIX funcionar, rode "ngrok http 8080" em outro terminal
#    e cole a URL https publica abaixo (com /webhooks/mercadopago no final).
# 3) Rode este script:  .\run-mercadopago.ps1
#
# OBS: estas variaveis valem SO para esta janela do PowerShell; nao afetam os
# testes (./mvnw test continua rodando em modo simulado).
# NUNCA compartilhe este arquivo preenchido com o token real.
# =====================================================================

$env:MERCADOPAGO_ENABLED          = "true"
$env:MERCADOPAGO_ACCESS_TOKEN     = "TEST-7111303542521423-060417-7adbb252894c7ca12a344194a0f444fb-581243566"
$env:MERCADOPAGO_PUBLIC_KEY       = "TEST-f2c033ce-3147-47a1-84ff-7ac13fc5eb1e"
$env:MERCADOPAGO_NOTIFICATION_URL = "http://localhost:8080/webhooks/mercadopago"
# Quando usar o ngrok, troque a linha acima por algo como:
# $env:MERCADOPAGO_NOTIFICATION_URL = "https://SEU-SUBDOMINIO.ngrok-free.app/webhooks/mercadopago"

if ($env:MERCADOPAGO_ACCESS_TOKEN -eq "COLE_SEU_ACCESS_TOKEN_AQUI") {
    Write-Warning "Voce ainda nao colou o Access Token no run-mercadopago.ps1."
}

Write-Host "Mercado Pago REAL habilitado. Subindo a aplicacao..." -ForegroundColor Green
.\mvnw.cmd quarkus:dev
