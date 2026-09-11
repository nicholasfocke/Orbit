// ===================================================================
//  ANIMAÇÃO DE UM PLANETA (usada nos cards do catálogo)
//
//  Aqui criamos uma função chamada "desenharPlanetaNoCard".
//  Ela é chamada uma vez para cada planeta, lá no arquivo catalog.js.
//
//  Cada card mostra o planeta parado no centro e um pequeno
//  satélite girando em volta dele (mesma ideia de seno e cosseno
//  do sistema solar). Ao passar o mouse, o satélite acelera.
// ===================================================================

function desenharPlanetaNoCard(canvas, planeta) {

  // Define o tamanho do desenho (o CSS deixa ele quadrado na tela).
  canvas.width = 200;
  canvas.height = 200;

  // Pincel para desenhar.
  const ctx = canvas.getContext("2d");

  // O centro do canvas (200 / 2 = 100).
  const centro = 100;

  // Ângulo atual do satélite e velocidade de rotação.
  let angulo = 0;
  let velocidade = 0.02;

  // Quando o mouse entra no card, gira mais rápido. Quando sai, volta ao normal.
  canvas.addEventListener("mouseenter", function () {
    velocidade = 0.08;
  });
  canvas.addEventListener("mouseleave", function () {
    velocidade = 0.02;
  });

  // Desenha um quadro da animação.
  function desenharQuadro() {

    // Limpa o desenho anterior.
    ctx.clearRect(0, 0, 200, 200);

    // Desenha o planeta: um círculo grande e colorido no centro.
    ctx.beginPath();
    ctx.arc(centro, centro, 42, 0, Math.PI * 2);
    ctx.fillStyle = planeta.color;
    ctx.fill();

    // Se for Saturno, desenha o anel (uma elipse achatada).
    if (planeta.hasRings) {
      ctx.beginPath();
      ctx.ellipse(centro, centro, 78, 20, 0, 0, Math.PI * 2);
      ctx.strokeStyle = planeta.color;
      ctx.lineWidth = 3;
      ctx.stroke();
    }

    // Move o satélite: soma um pouco no ângulo a cada quadro.
    angulo = angulo + velocidade;

    // Calcula a posição do satélite em volta do centro (raio de 80px).
    const x = centro + Math.cos(angulo) * 80;
    const y = centro + Math.sin(angulo) * 80;

    // Desenha o satélite: um círculo cinza pequeno.
    ctx.beginPath();
    ctx.arc(x, y, 6, 0, Math.PI * 2);
    ctx.fillStyle = "#cccccc";
    ctx.fill();

    // Repete para sempre (loop da animação).
    requestAnimationFrame(desenharQuadro);
  }

  desenharQuadro();
}
