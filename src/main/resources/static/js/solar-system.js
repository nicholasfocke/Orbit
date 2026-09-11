// ===================================================================
//  ANIMAÇÃO DO SISTEMA SOLAR (página inicial)
//
//  Desenha o Sol no centro e os planetas girando em volta, usando
//  um <canvas> (uma "folha de desenho" em branco).
//
//  Ideia principal: para fazer algo girar em volta de um ponto,
//  usamos seno e cosseno.
//      x = centro + cos(ângulo) * distância
//      y = centro + sen(ângulo) * distância
//  A cada quadro somamos um pouco no "ângulo", e o planeta anda
//  em círculo.
// ===================================================================

// 1) Pega a <div> do HTML onde a animação vai aparecer.
const areaHero = document.getElementById("heroCanvas");

// Só continua se essa div existir (ela só existe na página inicial).
if (areaHero) {

  // 2) Cria a folha de desenho (canvas) e coloca dentro da div.
  const canvas = document.createElement("canvas");
  canvas.width = areaHero.clientWidth;
  canvas.height = areaHero.clientHeight;
  areaHero.appendChild(canvas);

  // "ctx" é o pincel: é com ele que desenhamos tudo.
  const ctx = canvas.getContext("2d");

  // Se a janela mudar de tamanho, ajusta o canvas junto.
  window.addEventListener("resize", function () {
    canvas.width = areaHero.clientWidth;
    canvas.height = areaHero.clientHeight;
  });

  // 3) Guarda o ângulo atual de cada planeta na sua órbita.
  //    Começa todo mundo no ângulo 0.
  const angulos = [];
  for (let i = 0; i < PLANETS.length; i++) {
    angulos[i] = 0;
  }

  // 4) Esta função desenha UM quadro da animação.
  function desenharQuadro() {

    // O Sol fica no meio da tela.
    const centroX = canvas.width / 2;
    const centroY = canvas.height / 2;

    // (a) Pinta o fundo inteiro de azul escuro (isso "limpa" o quadro anterior).
    ctx.fillStyle = "#0b1020";
    ctx.fillRect(0, 0, canvas.width, canvas.height);

    // (b) Desenha o Sol: um círculo amarelo no centro.
    ctx.beginPath();
    ctx.arc(centroX, centroY, 22, 0, Math.PI * 2);
    ctx.fillStyle = "#ffc93c";
    ctx.fill();

    // (c) Desenha cada planeta.
    for (let i = 0; i < PLANETS.length; i++) {
      const planeta = PLANETS[i];

      // Distância deste planeta até o Sol, em pixels.
      // O planeta 0 fica a 70px, o 1 a 105px, o 2 a 140px... (70 + i * 35)
      const distancia = 70 + i * 35;

      // Planetas mais perto do Sol giram mais rápido (como na vida real).
      const velocidade = 0.02 / (i + 1);
      angulos[i] = angulos[i] + velocidade;

      // Transforma "ângulo + distância" numa posição (x, y) na tela.
      const x = centroX + Math.cos(angulos[i]) * distancia;
      const y = centroY + Math.sin(angulos[i]) * distancia;

      // Desenha a linha da órbita: um círculo cinza bem fraco.
      ctx.beginPath();
      ctx.arc(centroX, centroY, distancia, 0, Math.PI * 2);
      ctx.strokeStyle = "rgba(255, 255, 255, 0.12)";
      ctx.stroke();

      // Desenha o planeta na posição calculada.
      ctx.beginPath();
      ctx.arc(x, y, planeta.size, 0, Math.PI * 2);
      ctx.fillStyle = planeta.color;
      ctx.fill();

      // Se for Saturno, desenha um anel (uma elipse em volta dele).
      if (planeta.hasRings) {
        ctx.beginPath();
        ctx.ellipse(x, y, planeta.size + 8, planeta.size / 2, 0, 0, Math.PI * 2);
        ctx.strokeStyle = planeta.color;
        ctx.stroke();
      }
    }

    // (d) Pede ao navegador para chamar "desenharQuadro" de novo.
    //     Isso cria o loop: ~60 quadros por segundo = animação.
    requestAnimationFrame(desenharQuadro);
  }

  // 5) Dispara o primeiro quadro. Daí em diante ele se repete sozinho.
  desenharQuadro();
}
