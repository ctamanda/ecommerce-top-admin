# =====================================================================
# MODELO. Copie este arquivo para "run-mercadopago.ps1" e preencha com
# as SUAS credenciais. O arquivo real esta no .gitignore (nunca e commitado).
#
#   Copy-Item run-mercadopago.example.ps1 run-mercadopago.ps1
#
# 1) Pegue Access Token e Public Key no painel do Mercado Pago:
#    https://www.mercadopago.com.br/developers -> Suas integracoes -> Credenciais
#    (use as de TESTE para sandbox: o Access Token comeca com "TEST-").
# 2) Para o webhook do PIX confirmar sozinho, rode "ngrok http 8080" e use a URL
#    https publica em MERCADOPAGO_NOTIFICATION_URL (com /webhooks/mercadopago).
# 3) Rode:  .\run-mercadopago.ps1
# =====================================================================

$env:MERCADOPAGO_ENABLED          = "true"
$env:MERCADOPAGO_ACCESS_TOKEN     = "COLE_SEU_ACCESS_TOKEN_AQUI"
$env:MERCADOPAGO_PUBLIC_KEY       = "COLE_SUA_PUBLIC_KEY_AQUI"
$env:MERCADOPAGO_NOTIFICATION_URL = "http://localhost:8080/webhooks/mercadopago"
# Com ngrok, troque a linha acima por:
# $env:MERCADOPAGO_NOTIFICATION_URL = "https://SEU-SUBDOMINIO.ngrok-free.app/webhooks/mercadopago"

Write-Host "Mercado Pago REAL habilitado. Subindo a aplicacao..." -ForegroundColor Green
.\mvnw.cmd quarkus:dev
