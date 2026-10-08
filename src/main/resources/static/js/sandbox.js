// ===================================================================
//  SIMULADOR DE GRAVIDADE (sandbox e página do cenário)
//
//  1) Busca a lista de corpos no servidor (JSON) pelo endereço que
//     está em data-url na <div id="sandboxCanvas">.
//  2) A cada quadro, calcula a força da gravidade de TODOS os corpos
//     sobre cada corpo (lei de Newton: F = G * m1 * m2 / d²) e move
//     cada um um pouquinho.
//  3) Desenha tudo no <canvas>, com um rastro mostrando a órbita.
//
//  Unidades da simulação: distância em pixels e G = 1 (o mesmo G do
//  BodyService no Java).
// ===================================================================

const areaSim = document.getElementById("sandboxCanvas");

if (areaSim) {

  const G = 1;
  // Evita que a força fique infinita quando dois corpos ficam muito perto.
  const SUAVIZACAO = 25;
  // Quantos pedacinhos de física calculamos por quadro (mais = mais preciso).
  const PASSOS_POR_QUADRO = 8;
  const TAMANHO_RASTRO = 160;

  const canvas = document.createElement("canvas");
  areaSim.appendChild(canvas);
  const ctx = canvas.getContext("2d");

  let corposIniciais = [];   // como vieram do servidor (para o botão Reiniciar)
  let corpos = [];           // estado atual da simulação
  let escala = 1;            // pixels da tela por unidade da simulação
  let pausado = false;
  let marcador = null;       // ponto clicado pelo usuário (página do cenário)

  const btnPausar = document.getElementById("btnPausar");
  const btnReiniciar = document.getElementById("btnReiniciar");
  const sliderVelocidade = document.getElementById("velocidadeTempo");
  const formCorpo = document.getElementById("bodyForm");

  // ---------- Carregar os corpos ----------
  fetch(areaSim.dataset.url)
    .then(function (resposta) { return resposta.json(); })
    .then(function (dados) {
      corposIniciais = dados;
      reiniciar();
      ajustarTamanho();
      desenharQuadro();
    });

  // Copia os dados iniciais para o estado da simulação.
  function reiniciar() {
    corpos = corposIniciais.map(function (c) {
      return {
        name: c.name, color: c.color, mass: c.mass,
        size: c.size, rings: c.rings, central: c.central,
        x: c.posX, y: c.posY, vx: c.velX, vy: c.velY,
        rastro: []
      };
    });
  }

  // Ajusta o canvas ao tamanho da div e escolhe um zoom em que
  // o corpo mais distante caiba na tela.
  function ajustarTamanho() {
    canvas.width = areaSim.clientWidth;
    canvas.height = areaSim.clientHeight;

    let maiorDistancia = 100;
    corposIniciais.forEach(function (c) {
      maiorDistancia = Math.max(maiorDistancia, Math.hypot(c.posX, c.posY));
    });
    escala = Math.min(canvas.width, canvas.height) / 2 / (maiorDistancia + 30);
  }
  window.addEventListener("resize", ajustarTamanho);

  // ---------- Física ----------
  function passoDeFisica(dt) {
    // (a) Calcula a aceleração de cada corpo somando a atração de todos os outros.
    corpos.forEach(function (a) {
      a.ax = 0;
      a.ay = 0;
      corpos.forEach(function (b) {
        if (a === b) return;
        const dx = b.x - a.x;
        const dy = b.y - a.y;
        const dist2 = dx * dx + dy * dy + SUAVIZACAO;
        const dist = Math.sqrt(dist2);
        // aceleração = G * massa do outro / d², na direção do outro (dx/dist, dy/dist)
        const forca = G * b.mass / (dist2 * dist);
        a.ax += forca * dx;
        a.ay += forca * dy;
      });
    });

    // (b) Atualiza velocidade e depois posição (Euler semi-implícito).
    corpos.forEach(function (c) {
      c.vx += c.ax * dt;
      c.vy += c.ay * dt;
      c.x += c.vx * dt;
      c.y += c.vy * dt;
    });
  }

  // ---------- Desenho ----------
  // Converte coordenada da simulação para pixel da tela (origem no centro).
  function paraTelaX(x) { return canvas.width / 2 + x * escala; }
  function paraTelaY(y) { return canvas.height / 2 + y * escala; }

  function desenharQuadro() {
    if (!pausado) {
      const dtQuadro = (1 / 60) * Number(sliderVelocidade.value);
      for (let i = 0; i < PASSOS_POR_QUADRO; i++) {
        passoDeFisica(dtQuadro / PASSOS_POR_QUADRO);
      }
      corpos.forEach(function (c) {
        c.rastro.push({ x: c.x, y: c.y });
        if (c.rastro.length > TAMANHO_RASTRO) c.rastro.shift();
      });
    }

    ctx.fillStyle = "#0a0e1f";
    ctx.fillRect(0, 0, canvas.width, canvas.height);

    corpos.forEach(function (c) {
      // Rastro: linha passando pelas últimas posições.
      ctx.beginPath();
      c.rastro.forEach(function (p, i) {
        if (i === 0) ctx.moveTo(paraTelaX(p.x), paraTelaY(p.y));
        else ctx.lineTo(paraTelaX(p.x), paraTelaY(p.y));
      });
      ctx.strokeStyle = c.color + "55";   // mesma cor, semitransparente
      ctx.lineWidth = 1;
      ctx.stroke();

      const x = paraTelaX(c.x);
      const y = paraTelaY(c.y);

      ctx.beginPath();
      ctx.arc(x, y, c.size, 0, Math.PI * 2);
      ctx.fillStyle = c.color;
      ctx.fill();

      if (c.rings) {
        ctx.beginPath();
        ctx.ellipse(x, y, c.size + 8, c.size / 2, 0, 0, Math.PI * 2);
        ctx.strokeStyle = c.color;
        ctx.stroke();
      }

      if (!c.central) {
        ctx.fillStyle = "#8b93ad";
        ctx.font = "11px Segoe UI, sans-serif";
        ctx.fillText(c.name, x + c.size + 4, y - c.size - 2);
      }
    });

    // Marca o ponto escolhido para o novo corpo.
    if (marcador) {
      ctx.beginPath();
      ctx.arc(paraTelaX(marcador.x), paraTelaY(marcador.y), 6, 0, Math.PI * 2);
      ctx.strokeStyle = "#22d3ee";
      ctx.lineWidth = 2;
      ctx.stroke();
    }

    requestAnimationFrame(desenharQuadro);
  }

  // ---------- Botões ----------
  btnPausar.addEventListener("click", function () {
    pausado = !pausado;
    btnPausar.textContent = pausado ? "Continuar" : "Pausar";
  });
  btnReiniciar.addEventListener("click", reiniciar);

  // ---------- Clique para escolher posição (só na página do cenário) ----------
  if (formCorpo) {
    areaSim.classList.add("clicavel");

    canvas.addEventListener("click", function (evento) {
      const retangulo = canvas.getBoundingClientRect();
      // Pixel clicado -> coordenada da simulação.
      const x = (evento.clientX - retangulo.left - canvas.width / 2) / escala;
      const y = (evento.clientY - retangulo.top - canvas.height / 2) / escala;
      marcador = { x: x, y: y };

      // Velocidade para uma órbita circular em volta do corpo central:
      // v = raiz(G * M / r), apontando "de lado" (perpendicular ao Sol).
      const central = corposIniciais.find(function (c) { return c.central; });
      let vx = 0;
      let vy = 0;
      if (central) {
        const dx = x - central.posX;
        const dy = y - central.posY;
        const r = Math.hypot(dx, dy);
        if (r > 1) {
          const v = Math.sqrt(G * central.mass / r);
          vx = -dy / r * v;
          vy = dx / r * v;
        }
      }

      formCorpo.posX.value = x.toFixed(1);
      formCorpo.posY.value = y.toFixed(1);
      formCorpo.velX.value = vx.toFixed(2);
      formCorpo.velY.value = vy.toFixed(2);
    });
  }
}
