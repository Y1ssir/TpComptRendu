// app/consumer.js
const amqp = require('amqplib');

async function demarrerConsommateur(retries = 10, delay = 3000) {
  for (let i = 1; i <= retries; i++) {
    try {
      const conn    = await amqp.connect('amqp://admin:admin@rabbitmq');
      const channel = await conn.createChannel();
      const QUEUE   = 'commandes';

      await channel.assertQueue(QUEUE, { durable: true });
      console.log('👂 Consommateur en attente de messages...');

      channel.consume(QUEUE, (msg) => {
        if (msg) {
          const commande = JSON.parse(msg.content.toString());
          console.log('');
          console.log('🔔 NOUVELLE COMMANDE REÇUE !');
          console.log('   Client  :', commande.client);
          console.log('   Produit :', commande.produit);
          console.log('   ID      :', commande.id);
          console.log('📧 Email de confirmation envoyé (simulé)');
          console.log('');
          channel.ack(msg);
        }
      });
      return; // connexion réussie, on sort de la boucle
    } catch (err) {
      console.log(`⏳ RabbitMQ pas encore prêt (tentative ${i}/${retries})... nouvelle tentative dans ${delay/1000}s`);
      if (i === retries) throw new Error('❌ Impossible de se connecter à RabbitMQ');
      await new Promise(resolve => setTimeout(resolve, delay));
    }
  }
}

demarrerConsommateur();
