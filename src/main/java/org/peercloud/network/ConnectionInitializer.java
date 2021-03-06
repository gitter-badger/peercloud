package org.peercloud.network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * User: Stanislav Ovsyannikov
 * Date: 6/30/13
 * Time: 12:01 AM
 */
public class ConnectionInitializer extends ChannelInitializer<SocketChannel> {

    private ConnectionMode connectionMode;
    public ConnectionInitializer(ConnectionMode connectionMode) {
        this.connectionMode = connectionMode;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //pipeline.addLast("deflater", ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP));
        //pipeline.addLast("inflater", ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));

        //pipeline.addLast("decoder", new BigIntegerDecoder());
        //pipeline.addLast("encoder", new NumberEncoder());
        //pipeline.addLast("framer", new DelimiterBasedFrameDecoder(
        //        8192, Delimiters.lineDelimiter()));

        pipeline.addLast("codec", new NetworkMessageDecoder());
        //pipeline.addLast("decoder", new StringDecoder());
        //pipeline.addLast("encoder", new StringEncoder());
        //pipeline.addLast("decoder", new NetworkMessageDecoder());
// and then business logic.
// Please note we create a handler for every new channel
// because it has stateful properties.

        pipeline.addLast("handler", new ConnectionHandler(connectionMode));
    }
}