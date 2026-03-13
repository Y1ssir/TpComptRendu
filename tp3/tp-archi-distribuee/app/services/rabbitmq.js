// app/services/rabbitmq.js
const amqp = require('amqplib');

let channel;
const QUEUE = 'commandes';

async function connectRabbitMQ(retries = 10, delay = 3000) {
  for (let i = 1; i <= retries; i++) {
    try {
      const conn = await amqp.connect('amqp://admin:admin@rabbitmq');
      channel = await conn.createChannel();
      await channel.assertQueue(QUEUE, { durable: true });
      console.log('✅ RabbitMQ connecté');
      return;
    } catch (err) {
      console.log(`⏳ RabbitMQ pas encore prêt (tentative ${i}/${retries})... nouvelle tentative dans ${delay/1000}s`);
      if (i === retries) throw new Error('❌ Impossible de se connecter à RabbitMQ après plusieurs tentatives');
      await new Promise(resolve => setTimeout(resolve, delay));
    }
  }
}

function publierCommande(commande) {
  const msg = JSON.stringify(commande);
  channel.sendToQueue(QUEUE, Buffer.from(msg), { persistent: true });
  console.log('📤 Message publié dans RabbitMQ');
}

module.exports = { connectRabbitMQ, publierCommande };
